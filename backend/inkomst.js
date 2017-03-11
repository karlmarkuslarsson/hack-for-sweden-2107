var scb = require('./lib/scb');

var form_data = function () {
    return {
        "query": [
            {
                "code": "Region",
                "selection": {
                    "filter": "vs:RegionRiket99",
                    "values": [
                        "00"
                    ]
                }
            },
            {
                "code": "Kon",
                "selection": {
                    "filter": "item",
                    "values": [
                        "1",
                        "2",
                        "1+2"
                    ]
                }
            },
            {
                "code": "Inkomstklass",
                "selection": {
                    "filter": "item",
                    "values": [
                        "TOT",
                        "0",
                        "1-19",
                        "20-39",
                        "40-59",
                        "60-79",
                        "80-99",
                        "100-119",
                        "120-139",
                        "140-159",
                        "160-179",
                        "180-199",
                        "200-219",
                        "220-239",
                        "240-259",
                        "260-279",
                        "280-299",
                        "300-319",
                        "320-339",
                        "340-359",
                        "360-379",
                        "380-399",
                        "400-499",
                        "500-599",
                        "600-799",
                        "800-999",
                        "1000+"
                    ]
                }
            },
            {
                "code": "Tid",
                "selection": {
                    "filter": "item",
                    "values": [
                        "1991",
                        "1995",
                        "2000",
                        "2005",
                        "2010",
                        "2015"
                    ]
                }
            }
        ],
        "response": {
            "format": "json"
        }
    }
};

exports.get = function (done) {
    scb.get("http://api.scb.se/OV0104/v1/doris/sv/ssd/START/HE/HE0110/HE0110A/SamForvInk2", form_data(), done);
};