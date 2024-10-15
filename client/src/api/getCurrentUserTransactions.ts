import { BaseResponse, PaginationParam, TransactionResponse } from "src/interface/global-interface";
import axiosInstance, { endpoints } from "src/utils/axios";

export const getCurrentUserTransactions = async(param: PaginationParam) :Promise<BaseResponse<TransactionResponse>> =>{
    const res = await axiosInstance.get(endpoints.user.currentUserTransaction, {
        params:{
            ...param
        }
    });
    return res.data;
}