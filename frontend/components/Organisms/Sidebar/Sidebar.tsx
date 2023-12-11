import { SidebarProps } from "./Sidebar.utils";

const Sidebar = ({ routes }: SidebarProps) => {
  return (
    <aside className="w-52 min-h-screen fixed h-full" aria-label="Sidenav">
      <div className="overflow-y-auto py-5 px-3 h-full bg-gray-100 border-r border-gray-200 dark:bg-gray-800 dark:border-gray-700">
        <ul className="space-y-2">
          {routes.map((route) => {
            return (
              <li key={route.name}>
                <a
                  href={route.path}
                  className="flex items-center p-2 text-base font-normal text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
                >
                  <span className="ml-3">{route.name}</span>
                </a>
              </li>
            );
          })}
        </ul>
      </div>
    </aside>
  );
};
export default Sidebar;
