import yfinance as yf

def get_stock(ticker, start_date, end_date, interval, timesOverInterval):
    stock = yf.Ticker(ticker)
    hist = stock.history(start=start_date, end=end_date, interval=interval)
    return hist.to_json(orient='records')