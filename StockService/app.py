from flask import Flask, render_template, url_for, redirect, request
from flask_sqlalchemy import SQLAlchemy
from flask import Response, jsonify
from flask_cors import CORS, cross_origin
from AlgoDriver import Algo
import StockHandler as sh
import re

app = Flask(__name__)
CORS(app)

@app.route('/flask')
def howdy():
    response_body = "{\"msg\":\"hello from flask\"}"
    return Response(response_body,200)

@app.route('/flask/drive')
def runDriver():
    return "{\"msg\":\"" + Algo.run() + "\"}"

@app.route('/StockService/getAllTickers', methods=['GET'])
def get_all_tickers():
    print("Getting all tickers")
    try:
        tickers = sh.get_all_tickers()
        response_body = {"tickers": tickers}
        return jsonify(response_body), 200
    except:
        response_body = {"error": "An unknown error occurred while fetching tickers"}
        return jsonify(response_body), 500
    
@app.route('/StockService/getStockInfo', methods=['GET'])
def get_stock_data():
    ticker = request.get_json().get("ticker")
    if ticker is None:
        response_body = {"error": "No ticker provided"}
        return jsonify(response_body), 400
    
    try:
        data = sh.get_stock(ticker)
        
        response_body = {
            "ticker": data[0],
            "sector": data[1],
            "industry": data[2],
            "price": data[3]
            }
        return jsonify(response_body), 200
    except:
        response_body = {"error": "An unknown error occurred while fetching stock data for {ticker}".format(ticker=ticker)}
        return jsonify(response_body), 500

@app.route('/StockService/getStockListInfo', methods=['GET'])
def get_stock_list_data():
    tickers = request.get_json().get("tickers")
    if tickers is None:
        response_body = {"error": "Please provide a list of tickers"}
        return jsonify(response_body), 400

    try:
        stock_list = []
        for ticker in tickers:
            data = sh.get_stock(ticker)
            single_stock = {
                "ticker": data[0],
                "sector": data[1],
                "industry": data[2],
                "price": data[3]
                }
            stock_list.append(single_stock)

        response_body = {
            "stocks": stock_list
            }
        return jsonify(response_body), 200
    except:
        response_body = {"error": "An unknown error occurred while fetching stock data"}
        return jsonify(response_body), 500

@app.route('/StockService/stockInfoInWindow', methods=['GET'])
def get_info_over_interval():
    pass

@app.route('/StockService/stockInfoLastX', methods=['GET'])
def get_info_last_x():
    ticker = request.get_json().get("ticker")
    if ticker is None:
        response_body = {"error": "No ticker provided"}
        return jsonify(response_body), 400
    window = request.get_json().get("window")
    if window is None:
        response_body = {"error": "No window provided"}
        return jsonify(response_body), 400
    
    match = re.match(r'(\d+)([a-z]+)', window, re.I)
    if match:
        items = match.groups()
    else:
        response_body = {"error": "Invalid window format"}
        return jsonify(response_body), 400

    try:
        count = int(items[0])
        interval = items[1]

        if interval not in ["d", "wk", "mo", "y"]:
            raise ValueError("Invalid interval")
        if count < 1:
            raise ValueError("Invalid count")
        if count > 365:
            raise ValueError("Count must be less than 365")

        try:
            hist = sh.get_info_last_x(ticker, window, count, interval)

            hist.reset_index(inplace=True)
            hist_json = hist.to_json(orient='records')

            response_body = {"ticker": ticker, "log": hist_json, "window": window}
            return jsonify(response_body), 200
        except:
            response_body = {"error": "An unknown error occurred while fetching stock data for {ticker}".format(ticker=ticker)}
            return jsonify(response_body), 500
    except:
        response_body = {"error": "Invalid window format: you gave {window}, window is supposed to be in the format 'xy' where x is an integer and y is a time unit (d, wk, mo, y)".format(window=window)}
        return jsonify(response_body), 400

if __name__ == "__main__":
    app.run(debug=True)