export interface UseGetNewsProps {}
export interface Tag {
  id: number;
  name: string;
  slug: string;
}
export interface News {
  id?: number;
  title: string;
  content: string | TrustedHTML;
  slug?: string;
  image_relative_uri?: string;
  createdAt?: string;
  tags?: Tag[];
}
