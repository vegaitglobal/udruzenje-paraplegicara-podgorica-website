import AppTable from "@/components/Organisms/AppTable/AppTable";
import AddMapLocator from "@/components/Organisms/MapLocator/AddMapLocator";
import useGetLocations from "@/hooks/useGetLocations/useGetLocations";

const Page = async () => {
  const { locations } = await useGetLocations({
    locationParams: {
      categoryId: null,
      cityId: null,
      featureIds: [],
      name: "",
    },
  });
  const headings = ["name", "slug", "description"];
  return (
    <div>
      <AddMapLocator />
      <AppTable headings={headings} data={locations} />
    </div>
  );
};
export default Page;
