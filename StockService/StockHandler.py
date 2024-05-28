import yfinance as yf
import ssl
import pandas as pd
from datetime import timedelta
import traceback

def get_stock(ticker):
    stock = yf.Ticker(ticker)
    info = stock.info
    try:
        try:
            industry = info['industry']
        except:
            industry = "Unknown"
        try:
            sector = info['sector']
        except:
            sector = "Unknown"
        try:
            company = info['longName']
        except:
            company = "Unknown"
        try:
            # price needs to be a float
            price = stock.fast_info.last_price
            price = float(price)
        except:
            # if price is not available, set to -1.0
            price = -1.0

    except:
        print("Error fetching stock data")
        traceback.print_exc()
        print("failure on ticker: " + ticker)
        return None
    return (ticker, sector, industry, price, company)

def get_all_tickers():
    if hasattr(ssl, '_create_unverified_context'):
        ssl._create_default_https_context = ssl._create_unverified_context

    table = pd.read_html('https://en.wikipedia.org/wiki/List_of_S%26P_500_companies')
    df = table[0]
    tickers = df['Symbol'].tolist()
    return tickers

def get_close_price_on_date(ticker, date):
    stock = yf.Ticker(ticker)
    date = pd.to_datetime(date)

    # get day after date
    next_day = date + timedelta(days=1)

    year = date.year
    month = date.month
    day = date.day

    hist = stock.history(start=date, end=next_day, interval='1d')
    # check if hist is empty
    while hist.empty:
        date = date - timedelta(days=1)
        next_day = next_day - timedelta(days=1)
        hist = stock.history(start=date, end=next_day, interval='1d')

    print("hist")
    print(hist)
    return hist['Close'][0]

def get_info_over_interval(ticker, start_date, end_date):
    stock = yf.Ticker(ticker)
    hist = stock.history(start=start_date, end=end_date, interval='1d')
    return hist

def get_info_last_x(ticker, window, count, interval):
    if interval == "y":
        chosenInterval = "1wk"
    elif interval == "d" and count < 7:
        chosenInterval = "1m"
    elif interval == "d" and count < 30:
        chosenInterval = "1h"
    else:
        chosenInterval = "1d"

    try:
        hist = yf.download(tickers=ticker, period=window, interval=chosenInterval)
    except:
        print("Error fetching stock history")
    return hist
    

if __name__ == "__main__":
    print(get_stock("SPY"))
    print(get_all_tickers())