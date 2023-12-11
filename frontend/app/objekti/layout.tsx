import ToastProvider from "@/components/Atoms/ToastProvider/ToastProvider";
import Footer from "@/components/Organisms/Footer/Footer";
import Header from "@/components/Organisms/Header/Header";

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <div className="container mx-auto px-4">
        <Header />
        <div className="pt-[73px]">{children}</div>
      </div>
      <Footer />
    </>
  );
}
