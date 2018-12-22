from django.shortcuts import render

# Create your views here.
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
import json

@csrf_exempt
def add(request):
	if request.method == 'POST':
		body_unicode = request.body.decode('utf-8')
		body = json.loads(body_unicode)
		name = body['name']
		address = body['address']
		zipCode = body['zipCode']
		if(name!="" and address!="" and zipCode!=""):
			return JsonResponse({"output" : "Validasi Berhasil"})
		else:
			return JsonResponse({"output" : "Validasi Gagal"})
	else :
		return JsonResponse({"output" : "It's correct add"})

@csrf_exempt
def getAll(request):
    if request.method == 'POST':
    	body_unicode = request.body.decode('utf-8')
    	body = json.loads(body_unicode)
    	name = body['name']
    	address = body['address']
    	zipCode = body['zipCode']
    	return JsonResponse({"output" : name})
    else :
    	return JsonResponse({"output" : "It's correct get all"})