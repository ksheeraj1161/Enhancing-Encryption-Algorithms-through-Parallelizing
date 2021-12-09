### Algorithm:

Alice:

Select large prime numbers PA and QA

Corrupt nA = pA * qA

Compute euler function for nA

Pi(nA) = (pA-1)*(qA-1)

Select public component eA such that 1< eA< pi(nA) and gcd(eA, pi(nA)) =1

Compute private key dA 

eA*dA = 1mod pi(nA)




Bob:

Select large prime numbers pB and qB

Compute nB = pB * qB

Compute euler function for nB

Pi(nB)= (pB-1)*(qB-1)

Select public component eB such that 1< eB< pi(nB) and gcd (eB, pi(nB)) =1

Compute private key dB

eB*dB = 1mod pi(nB)



Store public key values in CA (certification authority)
Encryption c= (m^eB) mod nB
Decryption m= (c^dB) mod nB
