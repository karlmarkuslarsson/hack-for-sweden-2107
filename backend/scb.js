const request = require('request');

function as_keyvalue(data) {
    const cols = data.columns
            .map(s => s.text)
            .map(s => s.replace(/ /gi, " ")
                .replace(/,/gi, " ")
                .replace(/  /gi, "_")
                .replace(/ /g, "_")
            );

    const values = data.data
        .map(cell => cell.key.concat(cell.values))
        .map(function (cell) {
            return cell.map(function (v, i) { return [cols[i], v]; });
        })
        .map(function (cell) {
            const a = {};
            cell.forEach(function (itm, idx) {
                a[itm[0].toLocaleLowerCase()] = itm[1];
            });
            return a;
        });

    return values;
}

function as_keyvalue_resp(done) {
    return function (err, resp, data) {
        const jsonString = (data||'{}');
        const json = JSON.parse(jsonString.trim());
        done(err, as_keyvalue(json));
    }
}

exports.get = function (url, request_data, done) {
    request({
        url: url,
        method: "POST",
        json: request_data
    }, as_keyvalue_resp(done));
};