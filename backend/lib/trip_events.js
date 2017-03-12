const request = require('request');
const cachedRequest = require('cached-request')(request);

/* Set cache dir */
cachedRequest.setCacheDirectory(".cache/");

/*  */
exports.get = function (done) {
    done(null, {
        events: [
            {
                id: "accd86fe-7481-482c-a4ab-934881024930",
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
                id: "22d56ca7-763d-474a-a28a-32098c76c217",
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
                id: "dbb95588-7e15-4c74-8f4b-17bfc1e8d133",
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
                id: "7d22b460-f0ac-4978-b9c8-4d4dfc356519",
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
                id: "c432afa7-c7dc-4f21-b324-579e246f3e72",
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
                id: "fb96c8df-bca5-4efa-8e02-5e446bd5092a",
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
                id: "2badfb95-caf7-4fa6-8eaf-458b621a09d8",
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
                id: "54559aca-a8da-4e0c-9555-ebf398121989",
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
                id: "2f2501d2-d8e3-4231-9202-b83ac45bcccb",
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
                id: "c4765600-e780-4b87-90cd-643462f48eae",
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
                id: "37871097-4e88-4386-be53-7cdcf904bfe5",
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
                id: "3268363b-4eb7-4da5-a65b-c611dad820dd",
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
                id: "e2ffaafd-4caf-4b22-bbe0-847a5ad2ea41",
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
                id: "b7517976-b804-4830-b7d4-2e1acc2b3308",
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
                id: "2f2558cb-1553-4686-8d8c-452b8fe55efa",
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
                id: "3ed9d917-db8b-4960-a533-658200cc4e6b",
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
                id: "f2bfef8d-ec33-460c-9ef3-1c4e740d50ab",
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
                id: "cb5c67cc-b980-493c-9e7b-141b3e57780f",
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
                id: "23a2e2bd-e376-4edc-add5-8fa6223ed89c",
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
                id: "06ac791a-53df-45c4-a81b-8543ab289089",
                title: "Rooftop Tour",
                description: "See Stockholm from the roof top",
                price: "745 SEK",
                duration: 90,
                temp_rec: 15,
                lat: 59.3253499,
                lng: 18.0673271,
                img: "http://images.visitstockholm.com/40/381/40381d24-349a-4e18-b7c0-2dcbc0b9dd4b/rectangle_big_retina.jpg",
                tag: "sightseeing"
            },
            {
                id: "eb5b3f2c-84c8-4c3c-8609-8bdd8f630f8e",
                title: "Gondolen",
                description: "See Stockholm from one of Stockholms best viewpoints",
                price: "0 SEK",
                duration: 45,
                temp_rec: 5,
                lat: 59.3196123,
                lng: 18.0731519,
                img: "https://cdn.theculturetrip.com/images/56-3928702-14400733267e6d5ecfb96244c49c7c5c32bc2f3137.jpg",
                tag: "sightseeing"
            },
            {
                id: "828745b5-ec18-478f-a4fc-12cb758c94c6",
                title: "Gärdet",
                description: "All Stockholmers favourite parks to visit on weekends",
                price: "0 SEK",
                duration: 60,
                temp_rec: 20,
                lat: 59.3424934,
                lng: 18.0812583,
                img: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Starr%C3%A4ngen%2C_G%C3%A4rdet.JPG/500px-Starr%C3%A4ngen%2C_G%C3%A4rdet.JPG",
                tag: "nature"
            },
            {
                id: "bcb720e4-080a-47df-8501-c0f6b3757496",
                title: "Mallis",
                description: "Scandinavias biggest shopping centers",
                price: "0 SEK",
                duration: 90,
                temp_rec: 0,
                lat: 59.3701864,
                lng: 18.0033383,
                img: "http://mallofscandinavia.se/-/media/Unibail/Country~o~SE/MallofScandinavia/Centrumbilder/Accessbild.ashx",
                tag: "shopping"
            },
            {
                id: "a2d51025-7d38-4277-9266-4624b3870ec3",
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
                id: "5df4a235-dd6c-4ddf-ade5-5fed82954d3a",
                title: "Djuret",
                description: "Smart, vaulted restaurant with a menu focused on red meat, plus cured meats & European cheeses.",
                img: "http://ng.se/sites/default/files/Djuret.jpg"
            },
            {
                id: "062d994e-89f5-4315-bd20-7df2f53b4190",
                title: "A.G",
                lat: 59.3268215,
                lng: 18.0717194,
                description: "Cool restaurant with its own dry-aging room for a range of cuts of steak, plus non-meat options.",
                img: "https://s3-media2.fl.yelpcdn.com/bphoto/jkVrftf_vj0Kc8Fx7spZfQ/348s.jpg"
            },
            {
                id: "f3a5671d-fade-4e8a-9a61-d867654679d3",
                title: "Urban Deli",
                lat: 59.3268215,
                lng: 18.0717194,
                description: "The popular restaurant concept Urban Deli has expanded with a roof top bar at Sveavägen. At the 9th floor you find the green art park. Go for the food, the drinks, ...",
                img: "http://thatsup.se/content/img/article/14/mar/f1888863dac9489ef2f7dbb1cf047d32.jpg"
            },
            {
                id: "5c7535cf-24a8-4989-ab9b-e38f1ba82689",
                title: "Ling Long",
                lat: 59.3268215,
                lng: 18.0717194,
                description: "Ling Long pays homage to modern Asian cuisine with emphasis on quality Nordic ingredients. The restaurant is predominantly inspired the Sichuan province in China but also fuses influences from Bali and Singapore.",
                img: "http://static.thatsup.co/content/img/place/l/i/ling-long-9.jpg"
            },
            {
                id: "df44515f-57b0-4112-a332-9c6f0d4e9bba",
                title: "Surfers",
                lat: 59.3268215,
                lng: 18.0717194,
                description: "Surfers are like a Kinder egg - a kitsch tavern seemingly devoted digestible surfarkäk but serves delicious food from the innermost and most spicy China.",
                img: "https://images-2.svd.se/v2/images/3e0c269d-4c92-45da-9f0a-9d2b24421f9a?fit=crop&h=417&q=70&w=625&s=df9a31ef0ec942d1da05ccd7a156bf9c5c6dd0fc"
            },
            {
                id: "9d66de91-25ff-4776-8b70-e30725d233de",
                title: "Meatballs for the People",
                lat: 59.3268215,
                lng: 18.0717194,
                description: "The classic Swedish meatball. The shop offers 14 kinds of meatballs made of ingredients like elk, beef and salmon.",
                img: "http://static.thatsup.co/content/img/place/m/e/meatballs-for-the-people-5.jpg"
            },
            {
                id: "d9c4074e-4cdb-4f41-8978-4a63e8ee5859",
                title: "Burgers & Lobsters",
                lat: 59.3268215,
                lng: 18.0717194,
                description: "Burger and Lobster: serving wild live lobster and corn fed Nebraskan beef burgers since 2011. ",
                img: "http://www.stockholmfoodie.com/wp-content/uploads/2015/06/img_35681.jpg"
            },
            {
                id: "e3397903-ffd9-4f12-abdf-fed38823add9",
                title: "Gute Grill",
                lat: 59.3268215,
                lng: 18.0717194,
                description: "Steakhouse and barbecue restaurant",
                img: "http://stockholmfood.alltomstockholm.se/wp-content/uploads/sites/3/2015/10/DSCF6677.jpg"
            }
        ]
    })
};
