package com.karbyshev.randomuser.data.model;

import java.util.List;

public class UserModel {
    private final List<Results> results;

    private final Info info;

    public UserModel(List<Results> results, Info info) {
        this.results = results;
        this.info = info;
    }

    public List<Results> getResults() {
        return results;
    }

    public Info getInfo() {
        return info;
    }

    public static class Results {
        private final String gender;

        private final Name name;

        private final Location location;

        private final String email;

        private final Login login;

        private final Dob dob;

        private final Registered registered;

        private final String phone;

        private final String cell;

        private final Id id;

        private final Picture picture;

        private final String nat;

        public Results(String gender, Name name, Location location, String email, Login login,
                       Dob dob, Registered registered, String phone, String cell, Id id, Picture picture,
                       String nat) {
            this.gender = gender;
            this.name = name;
            this.location = location;
            this.email = email;
            this.login = login;
            this.dob = dob;
            this.registered = registered;
            this.phone = phone;
            this.cell = cell;
            this.id = id;
            this.picture = picture;
            this.nat = nat;
        }

        public String getGender() {
            return gender;
        }

        public Name getName() {
            return name;
        }

        public Location getLocation() {
            return location;
        }

        public String getEmail() {
            return email;
        }

        public Login getLogin() {
            return login;
        }

        public Dob getDob() {
            return dob;
        }

        public Registered getRegistered() {
            return registered;
        }

        public String getPhone() {
            return phone;
        }

        public String getCell() {
            return cell;
        }

        public Id getId() {
            return id;
        }

        public Picture getPicture() {
            return picture;
        }

        public String getNat() {
            return nat;
        }

        public static class Name {
            private final String title;

            private final String first;

            private final String last;

            public Name(String title, String first, String last) {
                this.title = title;
                this.first = first;
                this.last = last;
            }

            public String getTitle() {
                return title;
            }

            public String getFirst() {
                return first;
            }

            public String getLast() {
                return last;
            }
        }

        public static class Location {
            private final Street street;

            private final String city;

            private final String state;

            private final String country;

            private final String postcode;

            private final Coordinates coordinates;

            private final Timezone timezone;

            public Location(Street street, String city, String state, String country,
                            String postcode, Coordinates coordinates, Timezone timezone) {
                this.street = street;
                this.city = city;
                this.state = state;
                this.country = country;
                this.postcode = postcode;
                this.coordinates = coordinates;
                this.timezone = timezone;
            }

            public Street getStreet() {
                return street;
            }

            public String getCity() {
                return city;
            }

            public String getState() {
                return state;
            }

            public String getCountry() {
                return country;
            }

            public String getPostcode() {
                return postcode;
            }

            public Coordinates getCoordinates() {
                return coordinates;
            }

            public Timezone getTimezone() {
                return timezone;
            }

            public static class Street {
                private final int number;

                private final String name;

                public Street(int number, String name) {
                    this.number = number;
                    this.name = name;
                }

                public int getNumber() {
                    return number;
                }

                public String getName() {
                    return name;
                }
            }

            public static class Coordinates {
                private final String latitude;

                private final String longitude;

                public Coordinates(String latitude, String longitude) {
                    this.latitude = latitude;
                    this.longitude = longitude;
                }

                public String getLatitude() {
                    return latitude;
                }

                public String getLongitude() {
                    return longitude;
                }
            }

            public static class Timezone {
                private final String offset;

                private final String description;

                public Timezone(String offset, String description) {
                    this.offset = offset;
                    this.description = description;
                }

                public String getOffset() {
                    return offset;
                }

                public String getDescription() {
                    return description;
                }
            }
        }

        public static class Login {
            private final String uuid;

            private final String username;

            private final String password;

            private final String salt;

            private final String md5;

            private final String sha1;

            private final String sha256;

            public Login(String uuid, String username, String password, String salt, String md5,
                         String sha1, String sha256) {
                this.uuid = uuid;
                this.username = username;
                this.password = password;
                this.salt = salt;
                this.md5 = md5;
                this.sha1 = sha1;
                this.sha256 = sha256;
            }

            public String getUuid() {
                return uuid;
            }

            public String getUsername() {
                return username;
            }

            public String getPassword() {
                return password;
            }

            public String getSalt() {
                return salt;
            }

            public String getMd5() {
                return md5;
            }

            public String getSha1() {
                return sha1;
            }

            public String getSha256() {
                return sha256;
            }
        }

        public static class Dob {
            private final String date;

            private final int age;

            public Dob(String date, int age) {
                this.date = date;
                this.age = age;
            }

            public String getDate() {
                return date;
            }

            public int getAge() {
                return age;
            }
        }

        public static class Registered {
            private final String date;

            private final int age;

            public Registered(String date, int age) {
                this.date = date;
                this.age = age;
            }

            public String getDate() {
                return date;
            }

            public int getAge() {
                return age;
            }
        }

        public static class Id {
            private final String name;

            private final Object value;

            public Id(String name, Object value) {
                this.name = name;
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public Object getValue() {
                return value;
            }
        }

        public static class Picture {
            private final String large;

            private final String medium;

            private final String thumbnail;

            public Picture(String large, String medium, String thumbnail) {
                this.large = large;
                this.medium = medium;
                this.thumbnail = thumbnail;
            }

            public String getLarge() {
                return large;
            }

            public String getMedium() {
                return medium;
            }

            public String getThumbnail() {
                return thumbnail;
            }
        }
    }

    public static class Info {
        private final String seed;

        private final int results;

        private final int page;

        private final String version;

        public Info(String seed, int results, int page, String version) {
            this.seed = seed;
            this.results = results;
            this.page = page;
            this.version = version;
        }

        public String getSeed() {
            return seed;
        }

        public int getResults() {
            return results;
        }

        public int getPage() {
            return page;
        }

        public String getVersion() {
            return version;
        }
    }
}
