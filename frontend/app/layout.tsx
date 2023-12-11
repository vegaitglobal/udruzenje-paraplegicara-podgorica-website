import Header from "@/components/Organisms/Header/Header";
import "./globals.css";
import type { Metadata } from "next";
import { Inter } from "next/font/google";
import Footer from "@/components/Organisms/Footer/Footer";
import ToastProvider from "@/components/Atoms/ToastProvider/ToastProvider";

import "swiper/css";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Udruženje paraplegičara Podgorica",
  description: "Udruženje paraplegičara Podgorica",
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <ToastProvider>
          <div>{children}</div>
        </ToastProvider>
      </body>
    </html>
  );
}
