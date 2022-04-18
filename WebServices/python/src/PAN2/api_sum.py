import json
from flask import Flask,jsonify,request

app = Flask(__name__)

@app.route('/sum')
def sum():
    if (request.args.get('x')==None) :
        return jsonify(erreur = "L'argument x est manquant")
    x = int(request.args.get('x'))
    if  (request.args.get('y')==None) :
        return jsonify(erreur = "L'argument y est manquant")
    y = int(request.args.get('y'))
    return jsonify(message="Le resultat de la somme est :" + str(x+y))
    
if __name__ == "__main__" :
    app.run(debug=True)