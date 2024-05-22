import yfinance as yf

def get_stock(ticker, start_date, end_date, interval, timesOverInterval):
    stock = yf.Ticker(ticker)
    info = stock.fast_info
    actual_price = info.last_price
    return stock

def get_all_stocks():
    # get all tickers and company names from yfinance
    return None

if __name__ == "__main__":
    print(get_stock("SPY", "2020-01-01", "2020-01-02", "1d", 1))