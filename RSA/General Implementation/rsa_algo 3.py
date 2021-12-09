import time

def encryption(p: int,q: int,e: int, msg: str):
    n = int(p*q)
    f = int((p-1)*(q-1))
    cipher_text =[]
    for ch in msg:
         w = ord(ch) 
         w = pow(w,e,n)
         cipher_text.append(w)
    return cipher_text

def decryption(p: int,q: int,e: int, msg=[]):
    n = p * q
    f = (p-1)*(q-1)
    d = cal_d(e, f)
    plain_text =[]
    for ch in msg:
      if ch < 0:
           m=0-pow(ch,d)%n
      else:
           m=pow(ch,d,n)
      plain_text.append(m)
    message=''
    for i in plain_text:
        message+=chr(i+97)
    return message

def cal_d(e: int, f: int):
    # d -> ed = 1(mod z)        ; 1 < d < z
    d = 2
    while d < f:
        # check if this is the required `d` value
        if ((d*e) % f)==1:
            return d
        # else : increment and continue
        d += 1
        
def gcd(x: int, y: int):
    # GCD by Euclidean method
    small,large = (x,y) if x<y else (y,x)

    while small != 0:
        temp = large % small
        large = small
        small = temp
    return large

if __name__ =="__main__":
    p = int(input("Enter p : "))
    q = int(input("Enter q : "))
    e = int(input("Enter e : "))
    msg = input("Enter Message : ")
    starte = time.time()
    cipher_text = encryption(p, q, e,msg);
    print("Encrypted text :",cipher_text)
    ende= time.time()
    startd = time.time()
    print(f"Encryption Response time : {ende-starte}sec")
    plain_text= decryption(p, q, e,cipher_text)
    print("Decrypted text : ",  plain_text)
    endd= time.time()
    print(f"Decryption Response time : {endd-startd}sec")
