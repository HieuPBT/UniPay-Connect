import { AccessKeyResponse, BaseResponse, PaginationParam } from 'src/interface/global-interface';
import axiosInstance, { endpoints } from 'src/utils/axios';

export const getCurrentUserAccessKeys = async (
  param: PaginationParam
): Promise<BaseResponse<AccessKeyResponse>> => {
  const res = await axiosInstance.get(endpoints.user.currentUserAccessKey, {
    params: {
      ...param,
    },
  });
  return res.data;
};
