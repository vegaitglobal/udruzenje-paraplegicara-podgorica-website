export type UseFileUploadReturn = {
  sendDataAndUploadImage: (file: File, payload: any, url: string) => Promise<void>;
  response: UploadResponse;
  isLoading: boolean;
};

export type UploadResponse = {
  data?: any;
  error?: string;
};
