"use client";

import AppButton from "@/components/Atoms/AppButton/AppButton";
import AppInput from "@/components/Atoms/AppInput/AppInput";
import UploadImage from "@/components/Atoms/UploadImage/UploadImage";
import AppTable from "@/components/Organisms/AppTable/AppTable";
import { useHandleForm } from "@/hooks/useUploadImages/useUploadImages";
import { FormEvent, useState } from "react";
import { toast } from "react-toastify";

const Page = () => {
  const [name, setName] = useState("");
  const [image, setImage] = useState<File | null>(null);
  const { sendDataAndUploadImage, response, isLoading } = useHandleForm("thumbnail");
  const handleFormSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!image) {
      toast("Fajl nije izabran?");
      return;
    }
    const payload = {
      name: name,
    };

    const url = `${process.env.NEXT_PUBLIC_URL}categories`;

    sendDataAndUploadImage(image, payload, url);
    setName("");
    setImage(null);
    toast("Category successfully created");
  };
  return (
    <div>
      <h1>Dodaj kategoriju</h1>
      <form onSubmit={handleFormSubmit}>
        <AppInput
          name="name"
          label="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          type="text"
          required
        />

        <UploadImage image={image} setImage={setImage} />
        <AppButton type="submit" className="my-5">
          Upload
        </AppButton>
      </form>
    </div>
  );
};
export default Page;
