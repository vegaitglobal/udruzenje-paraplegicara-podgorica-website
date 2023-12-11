//@ts-nocheck

import { useState } from "react";
import { UploadResponse, UseFileUploadReturn } from "./useUploadImages.types";

export const useHandleForm = (fileKey: string): UseFileUploadReturn => {
  const [response, setResponse] = useState<UploadResponse>({});
  const [isLoading, setIsLoading] = useState(false);

  const sendDataAndUploadImage = async (file: File, payload, url: string) => {
    setIsLoading(true);
    const formData = new FormData();
    for (const key of Object.keys(payload)) {
      const value = payload[key];

      if (Array.isArray(value)) {
        // If the value is an array, loop through its elements and append each one
        value.forEach((arrayItem) => {
          formData.append(`${key}`, arrayItem);
        });
      } else {
        // If it's not an array, append it normally
        formData.append(key, value);
      }
    }

    formData.append(fileKey, file);

    try {
      const res = await fetch(url, {
        method: "POST",
        body: formData,
      });

      if (!res.ok) {
        throw new Error(`Server responded with ${res.status}`);
      }

      const data = await res.json();
      setResponse({ data });
    } catch (e: any) {
      setResponse({ error: e.message });
    } finally {
      setIsLoading(false);
    }
  };

  return { sendDataAndUploadImage, response, isLoading };
};
