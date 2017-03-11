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
                title: "wronger blepharohematidrosis",
                description: "Zoo lorem ipsum dolor dim sum...",
                price: "88 SEK",
                duration: 160,
                temp_rec: 12,
                lat: 12.9248,
                lng: 53.4786,
                img: "http://thecatapi.com/api/images/get?format=src&type=png&088846",
                tag: "amusement"
            },
            {
                id: "b17bd76b-8e81-44f6-a737-d6112566e725",
                title: "or hadrome",
                description: "Zoo lorem ipsum dolor dim sum...",
                price: "91 SEK",
                duration: 160,
                temp_rec: 0,
                lat: 13.6561,
                lng: 54.948,
                img: "http://thecatapi.com/api/images/get?format=src&type=png&d42e99",
                tag: "amusement"
            },
            {
                id: "ee2d7df5-8d05-4666-bcb0-916a76370851",
                title: "supportress smacking",
                description: "Zoo lorem ipsum dolor dim sum...",
                price: "80 SEK",
                duration: 160,
                temp_rec: 14,
                lat: 13.3919,
                lng: 53.1857,
                img: "http://thecatapi.com/api/images/get?format=src&type=png&4eccd0",
                tag: "amusement"
            },
            {
                id: "c5265da8-e3ee-459c-a236-9b9523fd6de2",
                title: "peeress oligotokous",
                description: "Zoo lorem ipsum dolor dim sum...",
                price: "80 SEK",
                duration: 160,
                temp_rec: 1,
                lat: 12.8682,
                lng: 54.3725,
                img: "http://thecatapi.com/api/images/get?format=src&type=png&feca5a",
                tag: "amusement"
            },
            {
                id: "9d8a01f0-fc12-4d07-b237-5ba41495be1b",
                title: "separateness monopodially",
                description: "Zoo lorem ipsum dolor dim sum...",
                price: "7 SEK",
                duration:160,
                temp_rec: 8,
                lat: 13.2205,
                lng: 54.7925,
                img: "http://thecatapi.com/api/images/get?format=src&type=png&d5e9df",
                tag: "amusement"
            }
        ],
        resturants: [
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
