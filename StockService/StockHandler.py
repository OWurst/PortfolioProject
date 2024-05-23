import yfinance as yf
import ssl
import pandas as pd

def get_stock(ticker):
    stock = yf.Ticker(ticker)
    info = stock.info
    industry = info['industry']
    sector = info['sector']
    price = stock.fast_info.last_price
    return (ticker, sector, industry, price)

def get_all_tickers():
    if hasattr(ssl, '_create_unverified_context'):
        ssl._create_default_https_context = ssl._create_unverified_context

    table = pd.read_html('https://en.wikipedia.org/wiki/List_of_S%26P_500_companies')
    df = table[0]
    tickers = df['Symbol'].tolist()
    return tickers

if __name__ == "__main__":
    print(get_stock("SPY"))
    print(get_all_tickers())