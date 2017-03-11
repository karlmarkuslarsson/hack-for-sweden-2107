const request = require('request');
const cachedRequest = require('cached-request')(request);

/* Set cache dir */
cachedRequest.setCacheDirectory(".cache/");

/*  */
exports.get = function (done) {
    done(null, {
        events: [
            {
                id: "7a883642-77e7-41fd-a87b-29b5fd00a58d",
                title: "Skansen",
                description: "Skansen is the first open-air museum and zoo in Sweden and is located on the island Djurgården.",
                price: "160 SEK",
                duration: 240,
                temp_rec: 18,
                lat: 59.3270426,
                lng: 18.1037456,
                img: "https://upload.wikimedia.org/wikipedia/commons/7/7b/Skansen_Entr%C3%A9_2015a.jpg",
                tag: "amusement"
            },
            {
                id: "088193f3-660f-4216-a148-c386062d462e",
                title: "Drottningholm Palace",
                description: "Well-preserved royal residence, with a Chinese Pavilion pleasure palace, theater & gardens.",
                price: "0 SEK",
                duration: 60,
                temp_rec: 18,
                lat: 59.3216935,
                lng: 17.8846313,
                img: "https://i.ytimg.com/vi/4B1eAabkr74/maxresdefault.jpg",
                tag: "museum"
            },
            {
                id: "c608e88a-0efb-47a0-9488-64e2551e523d",
                title: "Stockholm Palace",
                description: "Stockholm Palace or The Royal Palace is the official residence and major royal palace of the Swedish monarch. Stockholm Palace is located on Stadsholmen, in Gamla stan.",
                price: "0 SEK",
                duration: 160,
                temp_rec: 9,
                lat: 59.3268215,
                lng: 18.0717194,
                img: "http://www.nordstjernan.com/resizer/resizer.php?w=700&i=image-15096.jpg",
                tag: "museum"
            },
            {
                id: "78fb6fe9-8ece-48b5-8963-3ddd4e9ba9f6",
                title: "Gröna Lund",
                description: "Seasonal amusement park with roller coasters & thrill rides, competitive games & live concerts.",
                price: "120 SEK",
                duration: 240,
                temp_rec: 20,
                lat: 59.3233564,
                lng: 18.094196,
                img: "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Gr%C3%B6na_Lund_2016-07.jpg/250px-Gr%C3%B6na_Lund_2016-07.jpg",
                tag: "amusement"
            },
            {
                id: "28c6c315-636d-425a-b45c-2a6ebd3158ab",
                title: "Hammarbybacken",
                description: "Ski slope and in the summer a really nice viewpoint in the middle of the town",
                price: "0 SEK",
                duration: 90,
                temp_rec: 0,
                lat: 59.301238,
                lng: 18.10926,
                img: "http://media.swedentips.se/2016/01/hammarbybacken_stockholm-696x521.jpg",
                tag: "Adventure"
            },
            {
                id: "da83c50f-1f79-4c54-8c3b-0bcee13fa3fd",
                title: "Junibacken",
                description: "Astrid Lindgren musem for kids",
                price: "160 SEK",
                duration: 180,
                temp_rec: 14,
                lat: 59.3295295,
                lng: 18.0896431,
                img: "http://c8.alamy.com/comp/FEEFX7/house-of-pippi-longstocking-in-junibacken-childrens-museum-stockholm-FEEFX7.jpg",
                tag: "museum"
            },
            {
                id: "e675a4d7-6267-4ea0-ba99-d210a27011b8",
                title: "Gamla Stan",
                description: "Gamla stan, until 1980 officially Staden mellan broarna, is the old town of Stockholm, Sweden. Gamla stan consists primarily of the island Stadsholmen.",
                price: "0 SEK",
                duration: 90,
                temp_rec: 0,
                lat: 59.3250832,
                lng: 18.0668169,
                img: "http://totallystockholm.se/wp-content/uploads/2013/09/GAMLASTAN.jpg",
                tag: "museum"
            },
            {
                id: "cd4066da-8a60-4e5c-b0f1-1a2e0e217aa1",
                title: "Stadshuset",
                description: "Stockholm City Hall is the building of the Municipal Council for the City of Stockholm in Sweden. It stands on the eastern tip of Kungsholmen island, next to Riddarfjärden's northern shore and facing the islands of Riddarholmen and Södermalm.",
                price: "80 SEK",
                duration: 160,
                temp_rec: 14,
                lat: 59.3274506,
                lng: 18.0543456,
                img: "https://upload.wikimedia.org/wikipedia/commons/e/e6/Stockholms_stadshus_september_2011.jpg",
                tag: "amusement"
            },
            {
                id: "ea4066da-8a60-4e5c-b0f1-1a2e0e217bb1",
                title: "Globen",
                description: "Ericsson Globe is an indoor arena located in Stockholm Globe City, Johanneshov district of Stockholm, Sweden. The Ericsson Globe is the largest hemispherical building on Earth and took two and a half years to build.",
                price: "0 SEK",
                duration: 30,
                temp_rec: 10,
                lat: 59.2935725,
                lng: 18.0835501,
                img: "https://upload.wikimedia.org/wikipedia/commons/6/62/Globen_Stockholm_February_2007.jpg",
                tag: "architecture"
            },
            {
                id: "ff4066da-8a60-4e5c-b0f1-1a2e0e217ba1",
                title: "Fotografiska",
                description: "Fotografiska is a centre for contemporary photography in Stockholm, Sweden that opened on 21 May 2010.",
                price: "120 SEK",
                duration: 90,
                temp_rec: 0,
                lat: 59.3178415,
                lng: 18.0859104,
                img: "https://www.jimmynelson.com/media/images/vara-tjanster-fotografiska.jpg",
                tag: "museum"
            },
            {
                id: "ff4066da-8a60-4ecc-b0f1-1a2e0e217ab1",
                title: "ABBA: The Museum",
                description: "ABBA The Museum is an interactive exhibition about the pop-group ABBA that opened in Stockholm, Sweden in May 2013",
                price: "195 SEK",
                duration: 60,
                temp_rec: 0,
                lat: 59.3249222,
                lng: 18.0943349,
                img: "https://www.thelocal.se/userdata/images/1439479566_Abba.jpg",
                tag: "museum"
            },
            {
                id: "ff4066da-8a60-4ecc-b0f1-1a2e0e217ac1",
                title: "Moderna Museet",
                description: "Moderna Museet, Stockholm, Sweden, is a state museum for modern and contemporary art located on the island of Skeppsholmen in central Stockholm, opened in 1958.",
                price: "150 SEK",
                duration: 60,
                temp_rec: 0,
                lat: 59.3260466,
                lng: 18.0824992,
                img: "http://img.timeinc.net/time/asia/magazine/2009/0720/stockholm_04.jpg",
                tag: "museum"
            },
            {
                id: "ff4023da-8a60-4ecc-b0f1-1a2e0e217ac1",
                title: "Swedish National Museum of Science and Technology",
                description: "Interactive science & technology museum with family-friendly exhibits, a library & archives.",
                price: "150 SEK",
                duration: 60,
                temp_rec: 0,
                lat: 59.3325049,
                lng: 18.116777,
                img: "https://upload.wikimedia.org/wikipedia/commons/thumb/3/30/Tekniska_museet_2008b.jpg/250px-Tekniska_museet_2008b.jpg",
                tag: "museum"
            },
            {
                id: "ff4023da-8a60-4ecc-baf1-1a2e0e217ac1",
                title: "Kaknästornet (Tower)",
                description: "155m-high TV tower with an observation deck, 28th floor restaurant, 30th floor cafe-bar & gift shop.",
                price: "70 SEK",
                duration: 60,
                temp_rec: 0,
                lat: 59.3350448,
                lng: 18.1246041,
                img: "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Kakn%C3%A4stornet_2008x.jpg/250px-Kakn%C3%A4stornet_2008x.jpg",
                tag: "architecture"
            },






            {
                id: "b04b4120-6d8e-41e5-9633-ff97414a20bb",
                title: "Boat trip to Vaxholm",
                description: "Take the boat to Waxholm to discover the archipelago",
                price: "140 SEK",
                duration: 240,
                temp_rec: 10,
                lat: 59.3315207,
                lng: 18.0695279,
                img: "http://www.stromma.se/globalassets/sweden/stockholm/product_slideshows/excursions/day_trips/vaxholm/01_skb_vaxholm_1240x858.jpg?preset=slidetour",
                tag: "adventure"
            },
            {
                id: "5fc8fbfb-2219-47eb-942e-792481d180fb",
                title: "Olof Palme Memorial",
                description: "Swedens prime minister Olof Palme was murdered here 1986",
                price: "0 SEK",
                duration: 10,
                temp_rec: 0,
                lat: 59.336481,
                lng: 18.062538,
                img: "https://upload.wikimedia.org/wikipedia/commons/thumb/8/84/Olof_Palme_place_of_death.jpg/1280px-Olof_Palme_place_of_death.jpg",
                tag: "spot"
            },
            {
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
                title: "Ice-skate",
                description: "A nice way to see Stockholm in the winter, rent skates and enjoy a trip",
                price: "160 SEK",
                duration: 120,
                temp_rec: -5,
                lat: 59.2904721,
                lng: 18.1598473,
                img: "http://www.friluftsframjandet.se/Temp/akt_webb_skridskogrupp-js-1239286981.jpg",
                tag: "spot"
            },
            {
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
                title: "kayak",
                description: "A nice way to see Stockholm in the summer, rent a kayak and enjoy a trip",
                price: "200 SEK",
                duration: 120,
                temp_rec: 20,
                lat: 59.3200098,
                lng: 18.0291653,
                img: "https://s3-media1.fl.yelpcdn.com/bphoto/BCju8NQ8i9vBz3jp46uMRg/o.jpg",
                tag: "adventure"
            },
            {
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
                title: "boat sightseeing",
                description: "A nice way to see Stockholm in the summer is to take a hop-on-hop-off boat trip",
                price: "150 SEK",
                duration: 60,
                temp_rec: 15,
                lat: 59.3322116,
                lng: 18.0748544,
                img: "http://www.stromma.se/globalassets/sweden/stockholm/product_slideshows/sightseeing/boat/hopon_hopoff/01_ott_boat_1240x858.jpg",
                tag: "sightseeing"
            },
            {
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
                title: "Rooftop Tour",
                description: "See Stockholm from the roof top",
                price: "745 SEK",
                duration: 90,
                temp_rec: 15,
                lat: 59.3253499,
                lng: 18.0673271,
                img: "http://images.visitstockholm.com/40/381/40381d24-349a-4e18-b7c0-2dcbc0b9dd4b/rectangle_big_retina.jpg",
                tag: "sightseeing"
            }
            ,
            {
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
                title: "Gondolen",
                description: "See Stockholm from one of Stockholms best viewpoints",
                price: "0 SEK",
                duration: 45,
                temp_rec: 5,
                lat: 59.3196123,
                lng: 18.0731519,
                img: "https://cdn.theculturetrip.com/images/56-3928702-14400733267e6d5ecfb96244c49c7c5c32bc2f3137.jpg",
                tag: "sightseeing"
            }
            ,
            {
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
                title: "Gärdet",
                description: "All Stockholmers favourite parks to visit on weekends",
                price: "0 SEK",
                duration: 60,
                temp_rec: 20,
                lat: 59.3424934,
                lng: 18.0812583,
                img: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Starr%C3%A4ngen%2C_G%C3%A4rdet.JPG/500px-Starr%C3%A4ngen%2C_G%C3%A4rdet.JPG",
                tag: "nature"
            }
            ,
            {
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
                title: "Mallis",
                description: "Scandinavias biggest shopping centers",
                price: "0 SEK",
                duration: 90,
                temp_rec: 0,
                lat: 59.3701864,
                lng: 18.0033383,
                img: "http://mallofscandinavia.se/-/media/Unibail/Country~o~SE/MallofScandinavia/Centrumbilder/Accessbild.ashx",
                tag: "shopping"
            }
            ,
            {
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
                title: "Vasa Museum",
                description: "See the warship from 17th century  ",
                price: "0 SEK",
                duration: 60,
                temp_rec: 130,
                lat: 59.3280233,
                lng: 18.0913964,
                img: "http://hg2.com/wp-content/uploads/2015/07/stockholm-culture-vasamuseeta.jpg",
                tag: "museum"
            }



        ],
        restaurants: [
            {
                id: "09e8aaba-e368-427f-8e11-12e05d3f0caf",
                title: "Djuret",
                description: "Smart, vaulted restaurant with a menu focused on red meat, plus cured meats & European cheeses.",
                img: "http://ng.se/sites/default/files/Djuret.jpg"
            },
            {
                id: "5103922f-d0d4-4616-b718-e97618663e5e",
                title: "A.G",
                description: "Cool restaurant with its own dry-aging room for a range of cuts of steak, plus non-meat options.",
                img: "https://s3-media2.fl.yelpcdn.com/bphoto/jkVrftf_vj0Kc8Fx7spZfQ/348s.jpg"
            },
            {
                id: "325abc99-c36d-472e-b402-1f4d9e030571",
                title: "Urban Deli",
                description: "The popular restaurant concept Urban Deli has expanded with a roof top bar at Sveavägen. At the 9th floor you find the green art park. Go for the food, the drinks, ...",
                img: "http://thatsup.se/content/img/article/14/mar/f1888863dac9489ef2f7dbb1cf047d32.jpg"
            },
            {
                id: "325abc99-c36d-472e-b402-1f4d9e040571",
                title: "Ling Long",
                description: "Ling Long pays homage to modern Asian cuisine with emphasis on quality Nordic ingredients. The restaurant is predominantly inspired the Sichuan province in China but also fuses influences from Bali and Singapore.",
                img: "http://static.thatsup.co/content/img/place/l/i/ling-long-9.jpg"
            }
        ]
    })
};
