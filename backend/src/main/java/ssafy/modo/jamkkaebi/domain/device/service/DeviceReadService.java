package ssafy.modo.jamkkaebi.domain.device.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.modo.jamkkaebi.common.rabbitmq.service.RabbitSendService;
import ssafy.modo.jamkkaebi.domain.delivery.entity.Delivery;
import ssafy.modo.jamkkaebi.domain.delivery.respository.DeliveryRepository;
import ssafy.modo.jamkkaebi.domain.device.dto.request.DeviceDataReceiveDto;
import ssafy.modo.jamkkaebi.domain.device.dto.response.DeviceInfoResponseDto;
import ssafy.modo.jamkkaebi.domain.device.dto.response.DeviceStatusResponseDto;
import ssafy.modo.jamkkaebi.domain.device.entity.Device;
import ssafy.modo.jamkkaebi.domain.device.entity.DeviceStatus;
import ssafy.modo.jamkkaebi.domain.device.exception.DeviceNotFoundException;
import ssafy.modo.jamkkaebi.domain.device.repository.DeviceRepository;
import ssafy.modo.jamkkaebi.domain.vehicle.dto.response.VehicleInfo;
import ssafy.modo.jamkkaebi.domain.vehicle.entity.Vehicle;
import ssafy.modo.jamkkaebi.domain.vehicle.repository.VehicleRepository;

import java.io.IOException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeviceReadService {

    private final DeviceRepository deviceRepository;
    private final RabbitSendService rabbitSendService;
    private final ObjectMapper objectMapper;
    private final DeliveryRepository deliveryRepository;
    private final VehicleRepository vehicleRepository;

    public DeviceInfoResponseDto getDeviceInfo(String uuid) {

        Device device = deviceRepository.findById(uuid)
                .orElseThrow(DeviceNotFoundException::new);

        VehicleInfo vehicleInfo = null;

        if (device.getVehicle() != null) {
            Vehicle vehicle = device.getVehicle();
            vehicleInfo = VehicleInfo.builder()
                    .vehicleId(vehicle.getId())
                    .vehicleNumber(vehicle.getVehicleNumber()).build();
        }

        return DeviceInfoResponseDto.builder()
                .uuid(device.getUuid())
                .vehicleInfo(vehicleInfo)
                .build();
    }

    public Device getDevice(Long vehicleId) {
        return deviceRepository.findByVehicleId(vehicleId).orElseThrow(DeviceNotFoundException::new);
    }

    public boolean isDeviceHealthy(Device device) {
        return rabbitSendService.sendHealthCheckToDevice(device.getUuid());
    }

    public DeviceStatusResponseDto getDeviceStatus(String uuid) {

        Device device = getDeviceByUuid(uuid);
        boolean isHealthy = isDeviceHealthy(device);

        return DeviceStatusResponseDto.builder()
                .uuid(uuid)
                .status((isHealthy) ? DeviceStatus.CONNECTED : DeviceStatus.DISCONNECTED)
                .vehicleInfo(VehicleInfo.builder()
                        .vehicleId(device.getVehicle().getId())
                        .vehicleNumber(device.getVehicle().getVehicleNumber())
                        .build())
                .build();
    }

    public Device getDeviceByUuid(String uuid) {
        return deviceRepository.findById(uuid).orElseThrow(DeviceNotFoundException::new);
    }

    public Device getDeviceByDriverId(Long driverId) {
        return deviceRepository.findByDriverId(driverId).orElse(null);
    }

    public boolean getDeviceData(Long driverId) {
        Device device = deviceRepository.findByDriverId(driverId).orElse(null);
        return device != null && isDeviceHealthy(device);
    }

    public void handleDeviceData(String uuid, byte[] payload) throws IOException {

        Delivery delivery = deliveryRepository.findByDeviceUuidAndHasArrivedIsFalse(uuid);
        Vehicle vehicle = vehicleRepository.findByDeviceUuid(uuid).orElseThrow(DeviceNotFoundException::new);

        if (delivery != null) {
            DeviceDataReceiveDto dto = objectMapper.readValue(payload, DeviceDataReceiveDto.class);
            log.info("Received device data and mapped to DTO: {}", dto);

            // TODO: 배송 정보 업데이트
            // TODO: 웹소켓 구독자 확인
            // TODO: 웹소켓 구독자에게 DTO 전송
        }
    }
}