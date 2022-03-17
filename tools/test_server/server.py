from distutils.log import debug
from flask import Flask

app = Flask(__name__)

@app.route("/test_ok/")
def test_ok():
    return {"message": "ok"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug = True)