from flask import Flask, render_template, url_for, redirect, request
from flask_sqlalchemy import SQLAlchemy
from flask import Response
from flask_cors import CORS, cross_origin

app = Flask(__name__)
CORS(app)

@app.route('/flask')
def howdy():
    response_body = "{\"msg\":\"Hello From Flask\"}"
    return Response(response_body,200)


if __name__ == "__main__":
    app.run(debug=True)