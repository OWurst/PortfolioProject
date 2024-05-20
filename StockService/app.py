from flask import Flask, render_template, url_for, redirect, request
from flask_sqlalchemy import SQLAlchemy
from flask import Response
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



if __name__ == "__main__":
    app.run(debug=True)