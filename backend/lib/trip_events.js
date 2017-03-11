const request = require('request');
const cachedRequest = require('cached-request')(request);

/* Set cache dir */
cachedRequest.setCacheDirectory(".cache/");

/*  */
exports.get = function (done) {
    done(null, {
        events: [
            {
                id: "87db67f6-6e36-47a4-9a92-6700d38e491d",
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
                id: "0868501f-dca1-47e2-9a19-eb7090dc0508",
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
                id: "20d0d8f0-4da4-47df-a67e-73fd77e3b3fc",
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
                id: "c645ab72-4030-4e71-aac7-bd6606ed94cd",
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
                id: "b17bd76b-8e81-44f6-a737-d6112566e725",
                title: "Hammarbybacken",
                description: "Ski slope and in the summer a really nice viewpoint in the middle of the town",
                price: "0 SEK",
                duration: 90,
                temp_rec: 0,
                lat: 59.301238,
                lng: 18.10926,
                img: "https://upload.wikimedia.org/wikipedia/commons/a/a1/Hammarbybacken_january_2012.jpg",
                tag: "Adventure"
            },
            {
                id: "ee2d7df5-8d05-4666-bcb0-916a76370851",
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
                id: "",
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
                id: "ee2d7df5-8d05-4666-bcb0-916a76370851",
                title: "Stadshuset",
                description: "Stockholm City Hall is the building of the Municipal Council for the City of Stockholm in Sweden. It stands on the eastern tip of Kungsholmen island, next to Riddarfjärden's northern shore and facing the islands of Riddarholmen and Södermalm.",
                price: "80 SEK",
                duration: 160,
                temp_rec: 14,
                lat: 13.3919,
                lng: 53.1857,
                img: "https://upload.wikimedia.org/wikipedia/commons/e/e6/Stockholms_stadshus_september_2011.jpg",
                tag: "amusement"
            },












            {
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
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
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
                title: "Olof Palme Memorial",
                description: "Swedens prime minister Olof Palme was murdered here 1986",
                price: "0 SEK",
                duration: 10,
                temp_rec: 0,
                lat: 59.336481,
                lng: 18.062538,
                img: "hhttp://www.proletaren.se/sites/default/files/styles/article_images_thumb/public/2_web_olof_palme.jpg?itok=P77RMT4B",
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
                img: "hhttp://www.proletaren.se/sites/default/files/styles/article_images_thumb/public/2_web_olof_palme.jpg?itok=P77RMT4B",
                tag: "spot"
            }
        ],
        restaurants: [
            {
                id: "ad229547-d541-41f9-9f5f-e6dd371a3fb3",
                title: "Djuret",
                description: "Smart, vaulted restaurant with a menu focused on red meat, plus cured meats & European cheeses.",
                img: "http://ng.se/sites/default/files/Djuret.jpg"
            },
            {
                id: "43984e8c-8e32-48a8-9812-9363f58130ed",
                title: "A.G",
                description: "Cool restaurant with its own dry-aging room for a range of cuts of steak, plus non-meat options.",
                img: "https://s3-media2.fl.yelpcdn.com/bphoto/jkVrftf_vj0Kc8Fx7spZfQ/348s.jpg"
            },
            {
                id: "d30dafb5-8ee5-47f8-96e1-0a35da80d8f3",
                title: "Urban Deli",
                description: "The popular restaurant concept Urban Deli has expanded with a roof top bar at Sveavägen. At the 9th floor you find the green art park. Go for the food, the drinks, ...",
                img: "http://thatsup.se/content/img/article/14/mar/f1888863dac9489ef2f7dbb1cf047d32.jpg"
            }
        ]
    })
};
