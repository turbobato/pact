import os 

from flask import Flask, jsonify, requests

app=Flask(__name__)

bdd_url = "test"

@app.route('/retrieve-message')
def message():
    return jsonify(User = "Guilhem JAZERON", Message = "Salut, je vous parle")

