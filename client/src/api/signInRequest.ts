import { BaseResponse, SignInParams, SignInResponse } from 'src/interface/global-interface';
import axiosInstance, { endpoints } from 'src/utils/axios';

export const signInRequest = async (data: SignInParams): Promise<BaseResponse<SignInResponse>> => {
  const response = await axiosInstance.post(endpoints.auth.login, data);
  return response.data;
};
