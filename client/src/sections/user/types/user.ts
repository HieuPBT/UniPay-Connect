export interface IUserModel {
  id: number;
  username: string;
  fullName: string;
  email: string;
  phoneNumber: string;
  avatar?: string;
  role: string;
  active: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface IUserResponse {
  total: number;
  users: IUserModel[];
  size: number;
  page: number;
}
