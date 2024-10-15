import { BaseResponse, SubscriptionTierResponse } from 'src/interface/global-interface';
import axiosInstance, { endpoints } from 'src/utils/axios';

export const getSubscriptionTier = async (): Promise<BaseResponse<SubscriptionTierResponse[]>> => {
  const res = await axiosInstance.get<BaseResponse<SubscriptionTierResponse[]>>(
    endpoints.common.subscriptionTier
  );
  return res.data;
};
