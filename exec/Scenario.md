# 시연 시나리오

### 1. 뇌파 측정
    - 아두이노와 EEG click(INA114)을 통해 놔파 데이터 측정
    - RandomForest로 생성한 학습 모델에 실시간 뇌파 정보를 입력하여 현재 운전자 상태 출력

### 2. 운전자용 대시보드
    - 대시 보드 홈에서는 운전자 상태 정보와 경로 날씨 출력
    - 내비게이션 탭에서는 목적지 경로를 안내하고 주위 휴게시설 안내
    - 컨트롤 탬에서는 운전자가 차량 내부의 제어기를 제어할 수 있음(LED, AC, 창문, 진동)
    - 제어기와 연결된 ECU는 CAN 통신을 통해 제어 신호를 받아 제어 수행
    - 운전자용 보고서 탭에서는 누적 주행 거리 및 일일 근무 시간 등 운전자의 운행 정보와 관련된 정보 확인 가능

### 3. 졸음감지 + 자동제어
    - 대시보드 웹에서는 모달창으로 알림을 띄우는 동시에 미리 설정해둔 경고 음성 출력
    - 관리자용 페이지에서도 현재 운행중인 사람 중 졸음이 감지된 운전자를 알림
    - 운전자의 상태가 졸음인 경우 자동제어 신호를 전송하여 각각의 ECU에서 제어정보를 받아 LED, AC, 진동 모터를 작동하여 졸음을 깨우도록 함

### 4. 관리자 페이지
    - 실시간으로 운전자의 정보와 위치를 확인 가능
    - 운전자 리스트를 통해 전체 운전자의 관리 가능
    - 특정 운전자의 운행에 대한 히스토리를 확인
    - 운전자의 일정 기간 동안의 운전 정보를 누적하여 보고서화 
