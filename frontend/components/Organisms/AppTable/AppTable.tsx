import { AppTableProps } from "./AppTable.types";

const AppTable = <T extends Record<string, any>>({ headings, data }: AppTableProps<T>) => {
  return (
    <div className="mx-auto max-w-screen-xl px-4 lg:px-12">
      <div className="bg-white dark:bg-gray-800 relative shadow-md sm:rounded-lg overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
            <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
              <tr>
                {headings.map((heading, index) => (
                  <th key={index} scope="col" className="px-4 py-3">
                    {heading}
                  </th>
                ))}
              </tr>
            </thead>
            <tbody>
              {data.map((item, rowIndex) => (
                <tr key={rowIndex} className="border-b dark:border-gray-700">
                  {headings.map((key, colIndex) => (
                    <td key={colIndex} className="px-4 py-3">
                      {item[key]}
                    </td>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};
export default AppTable;
