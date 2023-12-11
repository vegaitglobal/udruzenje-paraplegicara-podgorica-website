/** @type {import('next').NextConfig} */
const nextConfig = {
  webpack(config) {
    config.module.rules.push({
      test: /\.svg$/i,
      use: ["@svgr/webpack"],
    });
    return config;
  },
  reactStrictMode: false,
  images: {
    domains: ["localhost"],
  },
  async redirects() {
    return [
      {
        source: "/",
        destination: "/pocetna",
        permanent: true, // Set to true for a permanent redirect (301), false for a temporary redirect (302)
      },
      {
        source: "/admin",
        destination: "/admin/admin-lokacija",
        permanent: true,
      },
    ];
  },
};

module.exports = nextConfig;
