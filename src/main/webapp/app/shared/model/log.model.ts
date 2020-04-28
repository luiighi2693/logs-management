export interface ILog {
  id?: string;
  url?: string;
  requestItem?: string;
  responseItem?: string;
  status?: number;
  method?: string;
  platform?: string;
  requestTime?: string;
  responseTime?: string;
  duration?: number;
  logged?: boolean;
  user?: string;
}

export const defaultValue: Readonly<ILog> = {
  logged: false
};
