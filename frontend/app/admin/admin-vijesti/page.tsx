"use client";
import AppButton from "@/components/Atoms/AppButton/AppButton";
import AppInput from "@/components/Atoms/AppInput/AppInput";
import UploadImage from "@/components/Atoms/UploadImage/UploadImage";
import RichTextEditor from "@/components/Molecules/QuillEditor/QuillEditor";
import { useHandleForm } from "@/hooks/useUploadImages/useUploadImages";
import { FormEvent, useState } from "react";
import { toast } from "react-toastify";

const Page = () => {
  const [title, setTitle] = useState("");
  const [slug, setSlug] = useState("");
  const [content, setContent] = useState("");
  const [image, setImage] = useState<File | null>(null);
  const { sendDataAndUploadImage, response, isLoading } = useHandleForm("image");

  const handleFormSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!image) {
      toast("Fajl nije izabran?");
      return;
    }
    const payload = {
      title: title,
      slug: slug.toLowerCase().replaceAll(" ", "-"),
      content: content,
    };

    const url = `${process.env.NEXT_PUBLIC_URL}news`;

    sendDataAndUploadImage(image, payload, url);
    setTitle("");
    setContent("");
    setSlug("");
    setImage(null);
    toast("News successfully created");
  };
  return (
    <div>
      <h1>Dodaj vijest</h1>
      <form onSubmit={handleFormSubmit}>
        <AppInput
          name="title"
          label="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          type="text"
          required
        />
        <AppInput
          name="slug"
          label="Slug"
          value={slug}
          onChange={(e) => setSlug(e.target.value)}
          type="text"
          required
        />
        <UploadImage image={image} setImage={setImage} />
        <RichTextEditor content={content} setContent={setContent} />
        <AppButton type="submit" className="my-5">
          Upload
        </AppButton>
      </form>
    </div>
  );
};
export default Page;
