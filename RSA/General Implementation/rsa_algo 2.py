import time

def encryption(p: int,q: int,e: int, msg: str):
    n = int(p*q)                #Calculate nB=pB*qB
    f = int((p-1)*(q-1))        #Calculate pi(nB)=(pB-1)(qB-1)
    cipher_text =[]
    for ch in msg:
         w = ord(ch) - 97       #-97 as the algorithm doesnt follow ascii and decrypts starting from a(a=1)
         if w<0:
            w = 0-pow(w,e)%n    #for caps and special characters shouldnt decrpt negative values
         else:
           w = pow(w,e,n)
         cipher_text.append(w)
    return cipher_text

def gcd(x: int, y: int):        #defining gcd function to be used
    small,large = (x,y) if x<y else (y,x)

    while small != 0:
        temp = large % small
        large = small
        small = temp
    return large

def cal_d(e: int, f: int):  #Calculate dB -> eB*dB = 1(mod pi) where 1 < d < pi
    d = 2
    while d < f:
        if ((d*e) % f)==1:  
            return d
        d += 1
        
def decryption(p: int,q: int,e: int, msg=[]):
    n = p * q               #Calculate nB=pB*qB
    f = (p-1)*(q-1)         #Calculate pi(nB)=(pB-1)(qB-1)
    d = cal_d(e, f)         #Calculate dB using function defined
    plain_text =[]
    for ch in msg:
      if ch < 0:
           m=0-pow(ch,d)%n
      else:
           m=pow(ch,d,n)
      plain_text.append(m)   #Writing Decrypted text
    message=''
    for i in plain_text:
        message+=chr(i+97)
    return message

if __name__ =="__main__":
    p = int(input("Enter p : ")) #Get input of p,q,e
    q = int(input("Enter q : "))
    e = int(input("Enter e : "))
    msg = input("Enter Message : ")
    start1 = time.time()
    cipher_text = encryption(p, q, e,msg);
    print("Encrypted text :",cipher_text)
    end1= time.time()
    print(f"Encryption Response time : {end1-start1}sec")    #to get response times,subtract start and end times
    
    start2 = time.time()
    plain_text= decryption(p, q, e,cipher_text)
    print("Decrypted text : ",  plain_text)
    end2= time.time()
    print(f"Decryption Response time : {end2-start2}sec")
