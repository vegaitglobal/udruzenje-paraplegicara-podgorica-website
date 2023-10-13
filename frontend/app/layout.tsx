import Header from "@/components/Organisms/Header/Header";
import "./globals.css";
import type { Metadata } from "next";
import { Inter } from "next/font/google";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Udruženje paraplegičara Podgorica",
  description: "Udruženje paraplegičara Podgorica",
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <div className="min-h-screen container mx-auto px-4 ">
          <Header />
          <div className="pt-20">{children}</div>
        </div>
      </body>
    </html>
  );
}
