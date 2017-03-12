const scb = require('./scb');

const form_data = function () {
    return {
        "query": [
            {
                "code": "Kon",
                "selection": {
                    "filter": "item",
                    "values": [
                        "1+2"
                    ]
                }
            },
            {
                "code": "Dem",
                "selection": {
                    "filter": "vs:DemografigruppÅlder",
                    "values": [
                        "16-24",
                        "25-34",
                        "35-44",
                        "45-54",
                        "55-74",
                        "tot16-74"
                    ]
                }
            },
            {
                "code": "ContentsCode",
                "selection": {
                    "filter": "item",
                    "values": [
                        "LE0108A2"
                    ]
                }
            }
        ],
        "response": {
            "format": "json"
        }
    };
};

exports.get = function (done) {
    scb.get("http://api.scb.se/OV0104/v1/doris/sv/ssd/START/LE/LE0108/LE0108B/LE0108T05", form_data(), done);
};