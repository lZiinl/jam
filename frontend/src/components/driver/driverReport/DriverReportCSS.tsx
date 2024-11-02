import styled from "styled-components";
import { GlassDiv } from "@/styles/driver/GlassmorphismStyle";

const CustomGlassDiv = styled(GlassDiv)`
  border-radius: 10px;
`;
export const ReportRightDiv = styled.div`
  width: 63.5%;
  min-width: 500px;
  height: 100%;
  display: flex;
  flex-direction: column;
  gap: 30px;
`;

export const ReportSummaryDiv = styled(GlassDiv)`
  width: 100%;
  height: 120px;
  display: flex;
  align-items: center;
`;

export const ReportSummaryBody = styled.div`
  width: 25%;
  height: 100%;
  display: flex;
  flex-direction: column;
  padding: 20px 5px;
  box-sizing: border-box;
`;
export const ReportSummaryContent = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 20px;
`;

export const ReportMainDiv = styled.div`
  width: 100%;
  height: 63%;
  display: flex;
  gap: 20px;
`;

export const DrivingReportDiv = styled(GlassDiv)`
  width: 32%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: start;
  padding: 10px;
  box-sizing: border-box;
  gap: 15px;
`;

export const DateReportDiv = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

export const DrivingReportList = styled(CustomGlassDiv)`
  width: 100%;
  height: 80px;
  padding: 12px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  gap: 4px;
`;

export const DrivingGraphWrapper = styled.div`
  width: 64%;
  height: 100%;
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

export const DrivingGraphDiv = styled(GlassDiv)`
  width: 100%;
  height: 47%;
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 20px 15px;
`;

export const HRLine = styled.hr`
  height: 70px;
  background-color: #fff;
  margin: 0;
`;
