from flask import Flask, render_template, url_for, redirect, request
from flask_sqlalchemy import SQLAlchemy
from flask import Response, jsonify
from flask_cors import CORS, cross_origin
from AlgoDriver import Algo
import StockHandler as sh

app = Flask(__name__)
CORS(app)

#   I am expecting this to eventually be a server that serves exactly two GET requests: one to get some number of top predicted stocks,
#   and one to get some number of bottom predicted stocks

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
        response_body = {"error": "An unknown error occurred while fetching stock data for {ticker}"}
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

if __name__ == "__main__":
    app.run(debug=True)