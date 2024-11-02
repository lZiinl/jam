import styled from "styled-components";
import { DriverText } from "@/components/driver/driverMain/DriverMainCSS";

const GoodDrivingTag = styled.div`
  width: 50px;
  height: 16px;
  padding: 4px 4px;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #cfffc7;
  border-radius: 4px;
`;

export const GoodDrivingBadge = () => {
  return (
    <GoodDrivingTag>
      <DriverText color="#10A957" fontSize="10px" fontWeight={400}>
        정상 운행
      </DriverText>
    </GoodDrivingTag>
  );
};

interface BadDrivingProps {
  drowsy?: number;
}

const BadDrivingTag = styled.div`
  width: 50px;
  height: 16px;
  padding: 4px 4px;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fff0f0;
  border-radius: 4px;
`;

export const BadDrivingBadge: React.FC<BadDrivingProps> = ({ drowsy }) => {
  return (
    <BadDrivingTag>
      <DriverText color="#FF5E5E" fontSize="10px" fontWeight={400}>
        졸음 {drowsy}회
      </DriverText>
    </BadDrivingTag>
  );
};
