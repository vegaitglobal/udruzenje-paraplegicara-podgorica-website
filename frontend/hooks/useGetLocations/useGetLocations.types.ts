export interface Location {
  id: number;
  name: string;
  description: string;
  slug: string;
  latitude: number;
  longitude: number;
  address: string;
  postalNumber: number;
  category: {
    id: number;
    name: string;
    relative_url: string;
  };
  accessibilityFeatures: [
    {
      id: number;
      name: string;
      relative_url: string;
    },
  ];
  images: [];
  thumbnail_url: string;
  email: string;
  phone: string;
}
export interface UseGetLocationsProps {
  name: string | null;
  categoryId: number | null;
  cityId: number | null;
  featureIds: number[] | null;
}
