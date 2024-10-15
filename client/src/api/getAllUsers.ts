import { BaseResponse, PaginationParam } from 'src/interface/global-interface';
import { IUserModel, IUserResponse } from 'src/sections/user/types/user';
import axiosInstance, { endpoints } from 'src/utils/axios';

export const getAllUser = async (param: PaginationParam): Promise<BaseResponse<IUserResponse>> => {
  const res = await axiosInstance.get(endpoints.user.allUser, {
    params: {
      ...param,
    },
  });
  return res.data;
};
