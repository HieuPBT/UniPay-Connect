import type { AxiosRequestConfig } from 'axios';

import axios from 'axios';

import { CONFIG } from 'src/config-global';

// ----------------------------------------------------------------------

const axiosInstance = axios.create({ baseURL: CONFIG.site.serverUrl });

axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => Promise.reject((error.response && error.response.data) || 'Something went wrong!')
);

export default axiosInstance;

// ----------------------------------------------------------------------

export const fetcher = async (args: string | [string, AxiosRequestConfig]) => {
  try {
    const [url, config] = Array.isArray(args) ? args : [args];

    const res = await axiosInstance.get(url, { ...config });

    return res.data;
  } catch (error) {
    console.error('Failed to fetch:', error);
    throw error;
  }
};

// ----------------------------------------------------------------------

export const endpoints = {
  auth: {
    login: '/api/authentication/login',
    currentUser: '/api/v1/user/currentUser',
  },
  common: {
    subscriptionTier: '/api/v1/billing/get-subscription-tier',
  },
  user: {
    currentUserTransaction: '/api/v1/transaction/get-transactions/current-user',
    currentUserAccessKey: '/api/v1/user/access-key/current-user',
    allUser: '/api/v1/user/',
  },
  accessKey: {
    create: '/api/v1/user/create-api-key',
    revoke: '/api/v1/user/revoke-api-key',
  },
  // chat: '/api/chat',
  // kanban: '/api/kanban',
  // calendar: '/api/calendar',
  // auth: {
  //   me: '/api/auth/me',
  //   signIn: '/api/auth/sign-in',
  //   signUp: '/api/auth/sign-up',
  // },
  // mail: {
  //   list: '/api/mail/list',
  //   details: '/api/mail/details',
  //   labels: '/api/mail/labels',
  // },
  // post: {
  //   list: '/api/post/list',
  //   details: '/api/post/details',
  //   latest: '/api/post/latest',
  //   search: '/api/post/search',
  // },
  // product: {
  //   list: '/api/product/list',
  //   details: '/api/product/details',
  //   search: '/api/product/search',
  // },
};
