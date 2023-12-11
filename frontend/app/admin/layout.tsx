import Sidebar from "@/components/Organisms/Sidebar/Sidebar";
import { RouteProps } from "@/components/Organisms/Sidebar/Sidebar.utils";
const routes: RouteProps[] = [
  { name: "Lokacije", path: "/admin/admin-lokacija" },
  {
    name: "Vijesti",
    path: "/admin/admin-vijesti",
  },
  {
    name: "Kategorije",
    path: "/admin/admin-kategorije",
  },
];
const Layout = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="flex ">
      <Sidebar routes={routes} />
      <div className="ml-56 mr-5 w-full p-3">{children}</div>
    </div>
  );
};
export default Layout;
