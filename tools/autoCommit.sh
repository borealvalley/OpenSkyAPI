echo "Pulling from Server ..."
git pull

while true
do
    echo "Commiting ..."
    git add --all
    git commit -m "Automatic Commit"
    echo "Pushing to Server ..."
    git push
    echo "Waiting 15 min"
    sleep 15m
done
