"use client";

import { FormEvent, useReducer, useState } from "react";
import AppButton from "../../Atoms/AppButton/AppButton";
import AppInput from "../../Atoms/AppInput/AppInput";
import AppSelect from "../../Atoms/AppSelect/AppSelect";
import AppMultipleSelect from "../../Atoms/AppMultipleSelect/AppMultipleSelect";
import { DropdownsData } from "../../Organisms/MapLocator/AddMapLocator.types";
import { TErrorType, validateFormData } from "./AddLocationForm.utils";
import { TCreateLocationPayload, TInitialFormState } from "./AddLocationForm.types";
import { toast } from "react-toastify";
import RichTextEditor from "../QuillEditor/QuillEditor";
import { useHandleForm } from "@/hooks/useUploadImages/useUploadImages";
import UploadImage from "@/components/Atoms/UploadImage/UploadImage";

interface IAddLocationForm {
  lat: number | undefined;
  lng: number | undefined;
  dropdownsData: DropdownsData;
}

enum FORM_ACTIONS {
  HANDLE_INPUT_CHANGE,
  HANDLE_ACCESSIBILITY_FEATURES_CHANGE,
  RESET_STATE,
}
const initialFormState: TInitialFormState = {
  name: "",
  description: "",
  address: "",
  postalNumber: 0,
  categoryId: 0,
  cityId: 0,
};
function reducer(state: TInitialFormState, action: { type: FORM_ACTIONS; payload?: any }) {
  switch (action.type) {
    case FORM_ACTIONS.HANDLE_INPUT_CHANGE:
      return {
        ...state,
        [action.payload.field]: action.payload.value,
      };
    case FORM_ACTIONS.RESET_STATE:
      return { ...state, ...initialFormState };
    default:
      return state;
  }
}
function AddLocationForm({
  lat,
  lng,
  dropdownsData: { categories, cities, accessibilityFeatures },
}: IAddLocationForm) {
  const [formState, dispatch] = useReducer(reducer, initialFormState);
  const [selectedAccessibilityFeatures, setSelectedAccessibilityFeatures] = useState<string[]>([]);
  const [descriptionFromEditor, setDescriptionFromEditor] = useState<string>("");
  const [image, setImage] = useState<File | null>(null);
  const { name, address, postalNumber } = formState;
  const [errors, setErrors] = useState<TErrorType>({});

  const { sendDataAndUploadImage, response, isLoading } = useHandleForm("thumbnail");

  const handleFormSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formErrors = validateFormData(formState, lat, lng, selectedAccessibilityFeatures);
    setErrors(formErrors);
    if (Object.keys(formErrors).length > 0) {
      return;
    }
    if (!lat || !lng) {
      return;
    }
    if (!image) {
      toast("Fajl nije izabran?");
      return;
    }
    const payload: TCreateLocationPayload = {
      ...formState,
      slug: name.toLowerCase().replaceAll(" ", "-").concat(`-lat-${lat}-lng-${lng}`),
      categoryId: +formState.categoryId,
      cityId: +formState.cityId,
      postalNumber: +formState.postalNumber,
      description: descriptionFromEditor,
      latitude: lat,
      longitude: lng,
      accessibilityFeatureIds: selectedAccessibilityFeatures.map((featureId) => +featureId),
    };

    sendDataAndUploadImage(image, payload);
    dispatch({ type: FORM_ACTIONS.RESET_STATE });
    setSelectedAccessibilityFeatures((prev) => []);
    setImage(null);
    setDescriptionFromEditor("");
    toast("Location successfully created");
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    dispatch({
      type: FORM_ACTIONS.HANDLE_INPUT_CHANGE,
      payload: {
        field: event.target.id,
        value: event.target.value,
      },
    });
  };
  const handleSelectChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    dispatch({
      type: FORM_ACTIONS.HANDLE_INPUT_CHANGE,
      payload: {
        field: event.target.id,
        value: event.target.value,
      },
    });
  };
  return (
    <form className="md:flex md:flex-col md:gap-3 mb-3" onSubmit={handleFormSubmit}>
      <div className="text-xl font-bold my-3 text-blue-700">Create location</div>
      <div>
        <AppInput
          name="name"
          label="Name"
          value={name}
          onChange={handleInputChange}
          type="text"
          required
        />
      </div>
      <div className="md:flex md:gap-3 ">
        <div className="h-100">
          <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
            Accessibility features
          </label>
          <AppMultipleSelect
            togglerTitle="Accessibility Features"
            options={accessibilityFeatures.map((feature) => {
              return {
                label: feature.name,
                value: feature.id,
              };
            })}
            selectedOptions={selectedAccessibilityFeatures}
            setSelectedOptions={setSelectedAccessibilityFeatures}
            errorMessage={errors.accessibilityFeatures}
          />
        </div>
        <AppSelect
          name="categoryId"
          label="Categories"
          options={categories.map((category) => {
            return {
              title: category.name,
              value: category.id,
            };
          })}
          value={formState.categoryId}
          onChange={handleSelectChange}
          errorMessage={errors?.categoryId}
        />
        <AppInput
          name="address"
          label="Address"
          value={address}
          onChange={handleInputChange}
          type="text"
        />
        <AppSelect
          name="cityId"
          label="City"
          options={cities.map((city) => {
            return {
              title: city.name,
              value: city.id,
            };
          })}
          onChange={handleSelectChange}
          errorMessage={errors.cityId}
        />
        <AppInput
          name="postalNumber"
          label="Postal number"
          value={postalNumber}
          onChange={handleInputChange}
          type="number"
          errorMessage={errors.postalNumber}
        />
      </div>

      <div className="md:flex md:gap-3">
        <AppInput
          name="latitude"
          label="Latitude"
          value={lat}
          disabled
          type="number"
          required
          placeholder="Please click on a map"
          errorMessage={errors.latitude}
        />
        <AppInput
          name="longitude"
          label="Longitude"
          value={lng}
          disabled
          type="number"
          required
          placeholder="Please click on a map"
          errorMessage={errors.longitude}
        />
      </div>
      <div>
        <UploadImage image={image} setImage={setImage} />
        <div className="h-100">
          <label className="block text-sm font-medium text-gray-900 dark:text-white mb-2">
            Description
          </label>
          <RichTextEditor content={descriptionFromEditor} setContent={setDescriptionFromEditor} />
        </div>
      </div>
      <div className="mt-2 md:flex md:justify-end">
        <AppButton type="submit">Create new location</AppButton>
      </div>
    </form>
  );
}

export default AddLocationForm;
