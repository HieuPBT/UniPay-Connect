export interface BaseResponse<Data> {
  code: number;
  message: string;
  result: Data;
}
export interface SignInResponse {
  jwt_token: string;
}

export interface SignInParams {
  username: string;
  password: string;
}

export interface UserResponse {
  id: number;
  username: string;
  fullName: string;
  email: string;
  phoneNumber: string;
  avatar: any;
}

export interface SubscriptionTierResponse {
  id: number;
  name: string;
  maxRequests: number;
  price: number;
}

export interface PaginationParam {
  page: number;
  size: number;
}

export interface TransactionResponse {
  total: number;
  size: number;
  page: number;
  transactions: TransactionModel[];
}

export interface TransactionModel {
  id: number;
  userId: number;
  amount: number;
  currency: string;
  status: string;
  orderId: string;
  createdAt: string;
}

export interface AccessKeyResponse {
  total: number;
  accessKeys: AccessKeyModel[];
  size: number;
  page: number;
}

export interface AccessKeyModel {
  id: number;
  apiKey: string;
  status: string;
  expires_at: string;
  revoked_at: string;
  created_at: string;
}
