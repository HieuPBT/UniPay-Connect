import { BaseResponse, UserResponse } from 'src/interface/global-interface';
import axiosInstance, { endpoints } from 'src/utils/axios';

export const getCurrentUser = async (): Promise<BaseResponse<UserResponse>> => {
  const res = await axiosInstance.get<BaseResponse<UserResponse>>(endpoints.auth.currentUser);
  return res.data;
};
