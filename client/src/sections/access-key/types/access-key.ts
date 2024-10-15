export interface IAccessKeyModel {
  id: number;
  apiKey: string;
  status: string;
  expires_at: string;
  revoked_at: string;
  created_at: string;
}

export interface IAccessKeyFilter {
  id: number;
  apiKey: string;
  status: string;
}
