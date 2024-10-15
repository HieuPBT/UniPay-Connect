import { BaseResponse } from 'src/interface/global-interface';
import axiosInstance, { endpoints } from 'src/utils/axios';

export const addAccessKey = async (): Promise<BaseResponse<any>> => {
  const res = await axiosInstance.post(endpoints.accessKey.create);
  return res.data;
};
