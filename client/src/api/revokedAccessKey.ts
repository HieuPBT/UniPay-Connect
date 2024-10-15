import { BaseResponse } from 'src/interface/global-interface';
import axiosInstance, { endpoints } from 'src/utils/axios';

export const revokedAccessKey = async (id: number): Promise<BaseResponse<any>> => {
  const res = await axiosInstance.post(endpoints.accessKey.revoke, {
    id: id,
  });
  return res.data;
};
