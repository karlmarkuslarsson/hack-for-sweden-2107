var request = require('request');

var url = "http://api.scb.se/OV0104/v1/doris/sv/ssd/BE/BE0101/BE0101A/BefolkningNy";

var form_data = function () {
    return {
        "query": [
            {
                "code": "ContentsCode",
                "selection": {
                    "filter": "item",
                    "values": [
                        "BE0101N1"
                    ]
                }
            },
            {
                "code": "Tid",
                "selection": {
                    "filter": "item",
                    "values": [
                        "2010",
                        "2011"
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
    request({
        url: url,
        method: "POST",
        json: form_data()
    }, done);
};